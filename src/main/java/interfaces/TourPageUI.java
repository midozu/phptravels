package interfaces;

public class TourPageUI {

	public static String TOUR_NAME = "//b[contains(text(),'%s')]";
	public static String DYNAMIC_BUTTON = "//span//*[contains(text(),'%s')]";
	public static String BOOKNOW_BUTTON = "//button[contains(.,'%s')]";
	public static String DYNAMIC_GUEST_FIELD = "//input[@placeholder='%s']";
	public static String TOTAL_AMOUNT = "//table[@class='table table-striped table-hover table-bordered table-responsive']//tr[4]/td[4]";
	public static String DEPOSIT_AMOUNT = "//table[@class='table table-striped table-hover table-bordered table-responsive']//tr[5]/td[4]";
	public static String MESSAGE = "//div[@class='center-block']//p";

}
