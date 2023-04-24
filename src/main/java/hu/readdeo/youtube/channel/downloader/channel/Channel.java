package hu.readdeo.youtube.channel.downloader.channel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Channel {
  private String name;
  private String quality;
  private String url;

}
