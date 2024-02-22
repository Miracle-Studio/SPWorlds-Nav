package ua.mei.spwn.config;

import dev.isxander.yacl3.api.controller.*;
import net.minecraft.text.*;

public class IntPercentFormatter implements ValueFormatter<Integer> {
    @Override
    public Text format(Integer value) {
        return Text.literal(String.format("%d%%", value));
    }
}
