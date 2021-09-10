```java
import java.time.Instant;

public class KakaoTraffic {

    private static class Duration {
        long startTime;
        long endTime;

        Duration(long startTime, long endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public long getStartTime() {
            return startTime;
        }

        public long getEndTime() {
            return endTime;
        }
    }

    public int solution(String[] lines) {
        Duration[] durationArr = new Duration[lines.length];

        for (int i = 0; i < lines.length; i++) {
            String[] splitStr = lines[i].split(" ");
            String dateTime = splitStr[0] + "T" + splitStr[1] + "Z";
            Instant finishTime = Instant.parse(dateTime);

            String durationSeconds = splitStr[2].substring(0, splitStr[2].length() - 1);

            double ms = Double.parseDouble(durationSeconds);
            int duration = (int) (ms * 1000) - 1;

            durationArr[i] = new Duration(finishTime.minusMillis(duration).toEpochMilli(), finishTime.toEpochMilli());
        }

        int maxCount = 0;
        for (Duration duration : durationArr) {
            long endTimeBase = duration.getEndTime();
            long afterOneMinBase = endTimeBase + 999;

            int count = countNumberOfRequests(durationArr, endTimeBase, afterOneMinBase);

            if (maxCount < count) maxCount = count;
        }

        return maxCount;
    }

    private int countNumberOfRequests(Duration[] durationArr, long endTimeBase, long afterOneMinBase) {
        int count = 0;

        for (Duration duration : durationArr) {
            long targetStartTime = duration.getStartTime();
            long targetEndTime = duration.getEndTime();

            if (targetStartTime > afterOneMinBase)
                continue;

            if (isIncludingRequest(targetStartTime, targetEndTime, endTimeBase, afterOneMinBase))
                count++;
        }

        return count;
    }

    private boolean isIncludingRequest(long targetStartTime, long targetEndTime, long endTimeBase, long afterOneMinBase) {
        return (targetEndTime >= endTimeBase && targetEndTime <= afterOneMinBase) ||
                (targetStartTime >= endTimeBase && targetStartTime <= afterOneMinBase) ||
                (targetStartTime < endTimeBase && targetEndTime > afterOneMinBase);
    }
}
```