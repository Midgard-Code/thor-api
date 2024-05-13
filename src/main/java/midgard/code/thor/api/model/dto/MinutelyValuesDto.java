package midgard.code.thor.api.model.dto;

public record MinutelyValuesDto(double cloudBase,
                                double cloudCeiling,
                                int cloudCover,
                                double dewPoint,
                                int freezingRainIntensity,
                                int humidity,
                                int precipitationProbability,
                                double pressureSurfaceLevel,
                                int rainIntensity,
                                int sleetIntensity,
                                int snowIntensity,
                                double temperature,
                                double temperatureApparent,
                                int uvHealthConcern,
                                int uvIndex,
                                int visibility,
                                int weatherCode,
                                double windDirection,
                                double windGust,
                                double windSpeed) {
}
