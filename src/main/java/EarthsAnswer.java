import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class EarthsAnswer {
    private String imageId;
    private Date date;

    public EarthsAnswer(@JsonProperty("image") String imageId,
                        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date date) {

        this.imageId = imageId;
        this.date = date;
    }

    public String getImageId() {
        return imageId;
    }

    public Date getDate() {
        return date;
    }
}
