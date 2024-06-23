package model;

public class OrderStatus {

    public enum OrderStatu {
        DANG_CHO_XU_LY("Đơn hàng đang chờ xử lý"),
        DA_XAC_NHAN("Đơn hàng đã xác nhận"),
        DA_GIAO("Đơn hàng đã giao"),
        BI_HUY("Đơn hàng bị hủy");

        private final String value;

        OrderStatu(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static OrderStatu getStatusByInt(int i) {
            switch (i) {
                case 1:
                    return DANG_CHO_XU_LY;
                case 2:
                    return DA_XAC_NHAN;
                case 3:
                    return DA_GIAO;
                case 4:
                    return BI_HUY;
                default:
                    return null;
            }
        }
    }
}
