package ua.mei.spwn.client;

import net.minecraft.client.*;
import net.minecraft.client.network.*;

import java.util.*;

public enum Server {
    SP("sp.spworlds.ru", "sp.spworlds.org"),
    SPm("spm.spworlds.ru", "spm.spworlds.org"),
    SPk("spk.spworlds.ru", "spk.spworlds.org");

    public final List<String> addresses;

    Server(String... addresses) {
        this.addresses = List.of(addresses);
    }

    public static Server getServer() {
        MinecraftClient client = MinecraftClient.getInstance();
        ServerInfo serverInfo = client.getCurrentServerEntry();

        return (serverInfo != null) ? Arrays.stream(values())
                .filter(server -> server.addresses.contains(serverInfo.address))
                .findFirst()
                .orElse(null) : null;
    }
}
