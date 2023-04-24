package hu.readdeo.youtube.channel.downloader.channel;

import hu.readdeo.youtube.channel.downloader.quality.QualityConfig;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ChannelConfig {

    @Value("${channels.path}") private String channelsPath;

    @Getter
    private List<Channel> channels;

    @PostConstruct
    private void init() throws IOException {
        loadChannelsFromCsv();
        for (Channel channel : channels) {
            log.info("{} {} {}", channel.getName(), channel.getQuality(), channel.getUrl());
        }
    }

    private void loadChannelsFromCsv() throws IOException {
        List<Channel> channels = new ArrayList<>();
        log.info("Loading channels from file: {}", channelsPath);
        File file = new File(channelsPath);
        InputStream inputStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");
            if (fields.length != 3 || line.startsWith("#")) {
                continue; // Skip invalid and commented lines
            }
            String name = fields[0];
            String quality = fields[1];
            String url = fields[2];

            Channel channel = new Channel(name, quality, url);
            channels.add(channel);
        }
        this.channels = channels;
    }
}
