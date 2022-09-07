package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;

public class CaptchUtil {

	// 1.created captcha object
	public static Captcha createdCaptcha(int width, int height) {
		return new Captcha.Builder(width, height).addBackground(new GradiatedBackgroundProducer())
				.addText(new DefaultTextProducer()).addNoise(new CurvedLineNoiseProducer()).build();
	}

	// 2.convert to binary

	public static String encodeBase64(Captcha captcha) {
		String imageData = "";
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(captcha.getImage(), "png", os);
			byte[] arr = Base64.getEncoder().encode(os.toByteArray());
			imageData = new String(arr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return imageData;

	}

}
