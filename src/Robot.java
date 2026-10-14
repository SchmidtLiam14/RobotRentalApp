public class Robot {
    String assetId;
    String name;
    String model;
    String serialNumber;
    String status;
    String primaryFunction;
    double rentalFee;
    String facilityId;

    public Robot(String assetId, String name, String model, String serialNumber, String status,
            String primaryFunction, double rentalFee, String facilityId) {
        this.assetId = assetId;
        this.name = name;
        this.model = model;
        this.serialNumber = serialNumber;
        this.status = status;
        this.primaryFunction = primaryFunction;
        this.rentalFee = rentalFee;
        this.facilityId = facilityId;
    }

    @Override
    public String toString() {
        return assetId + " | " + name + " | " + model + " | SN: " + serialNumber + " | " + status + " | "
                + primaryFunction + " | $" + rentalFee + " | facility: " + facilityId;
    }
}
