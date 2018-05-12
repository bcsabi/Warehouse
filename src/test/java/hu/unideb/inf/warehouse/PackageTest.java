package hu.unideb.inf.warehouse;

import hu.unideb.inf.warehouse.models.Package;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

public class PackageTest {

    private static Package package1 = new Package();

    @Test
    public void gettersAndSettersTest() {
        LocalDateTime time = LocalDateTime.now();
        package1.setId(1);
        package1.setPackageId("SZ123456789");
        package1.setSenderName("Sender1");
        package1.setSenderHomeAddress("Sender1Home");
        package1.setRecipientName("Recipient1");
        package1.setDeliveryAddress("DeliveryAddress");
        package1.setDateOfDispatch(time.minusDays(2));
        package1.setPackageType("Szabványos");
        package1.setWeight(50);
        package1.setStatus("Kiszállítva");
        package1.setExceptedDeliveryDate(time.plusDays(1));
        package1.setDeliveryDate(time.plusDays(1));

        Assert.assertEquals(1, package1.getId());
        Assert.assertEquals("SZ123456789", package1.getPackageId());
        Assert.assertEquals("Sender1", package1.getSenderName());
        Assert.assertEquals("Sender1Home", package1.getSenderHomeAddress());
        Assert.assertEquals("Recipient1", package1.getRecipientName());
        Assert.assertEquals("DeliveryAddress", package1.getDeliveryAddress());
        Assert.assertEquals(time.minusDays(2), package1.getDateOfDispatch());
        Assert.assertEquals("Szabványos", package1.getPackageType());
        Assert.assertEquals(50, package1.getWeight(),0);
        Assert.assertEquals("Kiszállítva", package1.getStatus());
        Assert.assertEquals(time.plusDays(1), package1.getExceptedDeliveryDate());
        Assert.assertEquals(time.plusDays(1), package1.getDeliveryDate());
    }

}
