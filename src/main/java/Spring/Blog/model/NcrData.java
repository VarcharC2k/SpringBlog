package Spring.Blog.model;

import lombok.Data;

import java.util.Date;

@Data
public class NcrData {
//    public String empName;
    private Root root;

    @Data
    public static class Root {
        public String containerName;
        public String company;
        public String workOrderNo;
        public String ProductItemCode;
        public String ProductDate;
        public int Qty;
    }
}
