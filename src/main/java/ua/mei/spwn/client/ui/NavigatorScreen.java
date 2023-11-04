package ua.mei.spwn.client.ui;

import io.wispforest.owo.ui.base.*;
import io.wispforest.owo.ui.component.*;
import io.wispforest.owo.ui.container.*;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.*;
import net.minecraft.text.*;
import org.jetbrains.annotations.*;
import ua.mei.spwn.client.*;
import ua.mei.spwn.client.api.*;
import ua.mei.spwn.client.api.types.*;
import ua.mei.spwn.client.hud.*;
import ua.mei.spwn.util.*;

import java.util.*;

public class NavigatorScreen extends BaseOwoScreen<FlowLayout> {
    public String server;

    public NavigatorScreen(String server) {
        this.server = server;
    }

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    protected void generateLayout(List<City> cities, FlowLayout layout) {
        int rows = (int) Math.ceil(cities.size() / 2);

        for (int i = 0; i < rows; i++) {
            FlowLayout row = Containers.horizontalFlow(Sizing.fill(100), Sizing.content());

            for (int j = 0; j < 2; j++) {
                int index = i + j * rows;

                ButtonComponent chooseButton = Components.button(SPWorldsNavClient.selectedCity != cities.get(index) ? Text.literal("Выбрать") : Text.literal("Убрать"), button -> {

                });
                chooseButton.onPress(button -> {
                    if (SPWorldsNavClient.selectedCity != cities.get(index)) {
                        SPWorldsNavClient.selectedCity = cities.get(index);
                        MinecraftClient.getInstance().setScreen(null);
                    } else {
                        SPWorldsNavClient.selectedCity = null;
                        chooseButton.setMessage(Text.literal("Выбрать"));
                    }
                });
                chooseButton.horizontalSizing(Sizing.fill(100)).margins(Insets.top(4));

                if (index < cities.size()) {
                    row.child(
                            Containers.verticalFlow(Sizing.fill(50), Sizing.content())
                                    .child(Containers.verticalFlow(Sizing.fill(98), Sizing.content())
                                            .child(Containers.verticalFlow(Sizing.fill(100), Sizing.content())
                                                    .child(Containers.horizontalFlow(Sizing.fill(100), Sizing.content())
                                                            .child(Containers.verticalFlow(Sizing.fill(50), Sizing.content())
                                                                    .child(Components.label(Text.literal(cities.get(index).name()))
                                                                            .horizontalTextAlignment(HorizontalAlignment.LEFT)
                                                                            .horizontalSizing(Sizing.fill(90))
                                                                    )
                                                            )
                                                            .child(Containers.verticalFlow(Sizing.fill(50), Sizing.content())
                                                                    .child(Components.label(SPMath.thread(cities.get(index).x(), cities.get(index).z()).getText(cities.get(index).x(), cities.get(index).z()))
                                                                            .horizontalTextAlignment(HorizontalAlignment.RIGHT)
                                                                            .horizontalSizing(Sizing.fill(90))
                                                                    )
                                                            )
                                                    )
                                                    .child(chooseButton)
                                                    .margins(Insets.of(8))
                                            )
                                            .surface(SPWorldsSurface.BEDROCK_PANEL)
                                    )
                                    .horizontalAlignment(HorizontalAlignment.CENTER)
                                    .margins(Insets.bottom(4))
                    );
                }
            }

            layout.child(row);
        }
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        FlowLayout layout = Containers.verticalFlow(Sizing.fill(100), Sizing.content());

        TextBoxComponent searchBox = Components.textBox(Sizing.fill(100));
        searchBox.setPlaceholder(Text.literal("Поиск"));
        searchBox.onChanged().subscribe(string -> {
            if (this.server.equals("sp")) {
                SPWorldsNavClient.asyncTasksService.addTask(MiracleApi::getCitiesSp, result -> {
                    List<City> cities = (List<City>) result;
                    List<City> filteredList = new ArrayList<>();

                    for (City city : cities) {
                        if (SPMath.search(city.name(), searchBox.getText())) {
                            filteredList.add(city);
                        }
                    }

                    layout.clearChildren();
                    generateLayout(filteredList, layout);
                }, exception -> {

                });
            } else {
                SPWorldsNavClient.asyncTasksService.addTask(MiracleApi::getCitiesSpm, result -> {
                    List<City> cities = (List<City>) result;
                    List<City> filteredList = new ArrayList<>();

                    for (City city : cities) {
                        if (SPMath.search(city.name(), searchBox.getText())) {
                            filteredList.add(city);
                        }
                    }

                    layout.clearChildren();
                    generateLayout(filteredList, layout);
                }, exception -> {

                });
            }
        });

        if (this.server.equals("sp")) {
            SPWorldsNavClient.asyncTasksService.addTask(MiracleApi::getCitiesSp, result -> {
                List<City> cities = (List<City>) result;

                generateLayout(cities, layout);
            }, exception -> {

            });
        } else {
            SPWorldsNavClient.asyncTasksService.addTask(MiracleApi::getCitiesSpm, result -> {
                List<City> cities = (List<City>) result;

                generateLayout(cities, layout);
            }, exception -> {

            });
        }

        rootComponent.child(Containers.verticalFlow(Sizing.fill(80), Sizing.fill(90))
                        .child(Containers.verticalFlow(Sizing.fill(100), Sizing.fill(10))
                                .child(searchBox)
                        )
                        .child(Containers.verticalScroll(Sizing.fill(100), Sizing.fill(90), layout))
                )
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .verticalAlignment(VerticalAlignment.CENTER)
                .surface(Surface.VANILLA_TRANSLUCENT);
    }
}
