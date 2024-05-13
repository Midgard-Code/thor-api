package midgard.code.thor.api.model.dto;

public record LocationDto(double lat,
                          double lon,
                          String name,
                          String type) {
}
