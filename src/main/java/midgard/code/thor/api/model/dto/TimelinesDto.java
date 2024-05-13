package midgard.code.thor.api.model.dto;

import java.util.List;

public record TimelinesDto(List<HourlyDto> hourly,
                           List<MinutelyDto> minutely,
                           List<DailyDto> daily) {
}
