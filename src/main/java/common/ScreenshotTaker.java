package common;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.openqa.selenium.*;
import org.openqa.selenium.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ScreenshotTaker {
    private static ScreenshotTaker INSTANCE;

    private ScreenshotTaker() {
    }

    public static ScreenshotTaker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ScreenshotTaker();
        }
        return INSTANCE;
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] takeScreenshot(WebDriver webDriver) {
        return  ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    @SneakyThrows
    @Attachment(value = "Screenshot with element", type = "image/png")
    public static byte[] takeScreenshot(WebDriver webDriver, WebElement webElement) {
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        return ImageWithOutlinedElement(screenshot, webElement);
    }

    private static byte[] ImageWithOutlinedElement(File file, WebElement webElement) throws IOException {
        BufferedImage image = ImageIO.read(file);
        Rectangle elementBorder = webElement.getRect();
        addRectangle(image, elementBorder);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }

    private static void addRectangle(BufferedImage img, Rectangle rectangle) {
        var graphics = (Graphics2D) img.getGraphics();
        graphics.setColor(Color.RED);
        graphics.setStroke(new BasicStroke(3));
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.getWidth(), rectangle.getHeight());
    }
}
