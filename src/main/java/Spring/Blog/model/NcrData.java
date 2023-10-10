package Spring.Blog.model;

import lombok.Data;

@Data
public class NcrData {
//    public String empName;
    private Root root;

    @Data
    public static class Root {
        public String containerName;
    }
}
