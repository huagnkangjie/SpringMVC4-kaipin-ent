package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *　文件说明：生成验证码用servlet
 * 
 *　版权所有：易房网络科技股份有限公司 　
 * 创建者：huang_kj 
 */

public class ValidateCodeServlet extends HttpServlet implements Serializable{

	public static final String Session_Name = "VALIDATION_CODE";

	private static final long serialVersionUID = -7080388860058814634L;

	private int width = 60;
	private int height = 20;
	private int codeCount = 4;
	private int x = 0;
	private int fontHeight;
	private int codeY;

	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	@Override
	public void init() throws ServletException {
		String strWidth = this.getInitParameter("width");
		String strHeight = this.getInitParameter("height");
		String strCodeCount = this.getInitParameter("codeCount");

		try {
			if (strWidth != null && strWidth.length() != 0) {
				width = Integer.parseInt(strWidth);
			}
			if (strHeight != null && strHeight.length() != 0) {
				height = Integer.parseInt(strHeight);
			}
			if (strCodeCount != null && strCodeCount.length() != 0) {
				codeCount = Integer.parseInt(strCodeCount);
			}
		} catch (NumberFormatException e) {
			 e.printStackTrace();
		}
		x = width / (codeCount + 1);
		fontHeight = height - 2;
		codeY = height - 4;

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, java.io.IOException {

		BufferedImage buffImg = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = buffImg.createGraphics();

		Random random = new Random();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);

		Font font = new Font("Fixedsys", Font.PLAIN | Font.BOLD, fontHeight);
		g.setFont(font);

		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width - 1, height - 1);

		g.setColor(new Color(200,200,255));
		for (int i = 0; i < 160; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		StringBuffer randomCode = new StringBuffer();
		int red = 0, green = 0, blue = 0;

		for (int i = 0; i < codeCount; i++) {
			String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
			red = random.nextInt(160);
			green = random.nextInt(160);
			blue = random.nextInt(160);

			g.setColor(new Color(red, green, blue));
			g.drawString(strRand, (i + 1) * x - 6, codeY);

			randomCode.append(strRand);
		}
		HttpSession session = req.getSession();
		session.setAttribute("VALIDATION_CODE", randomCode.toString());

		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setDateHeader("Expires", 0);

		resp.setContentType("image/jpeg");

		ServletOutputStream sos = resp.getOutputStream();
		ImageIO.write(buffImg, "jpeg", sos);
		sos.close();
	}
}
