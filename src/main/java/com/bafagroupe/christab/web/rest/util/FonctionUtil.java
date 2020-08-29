package com.bafagroupe.christab.web.rest.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import javax.swing.*;
import javax.xml.bind.DatatypeConverter;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sun.misc.BASE64Decoder;


public class FonctionUtil implements Serializable{


	public Integer GenerateTimeBetweenDates(String dateDebut, String dateFin) throws ParseException {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
		SimpleDateFormat myFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = myFormat2.parse(dateDebut);
		Date date2 = myFormat2.parse(dateFin);
		/*Long diff = ( (date2.getTime() - date1.getTime())  / (1000*60*60*24) );
		int days = diff.intValue();*/
		Long diff = (date2.getTime() - date1.getTime())  / 86400000;
		int days = diff.intValue();
		return days;
		// return Math.abs(diff);
	}

	/**************** Génération d'un code de 5 chiffres pour le numéro de la facture **************/
	public Integer getRandomNumber() {
		// It will generate 5 digit random Number from 0 to 99999
		Random rnd = new Random();
		int number = rnd.nextInt(99999);

		// this will convert any number sequence into 5 characters.
		return number;
	}

	/************************ Récupérer le chemin des fichiers ****************/
	public String getFilePath(String file) throws IOException {
		// String result = null;
		byte[] p = Files.readAllBytes(Paths.get("/fichiers/"+file));
		// result = ServletUriComponentsBuilder.fromCurrentContextPath().path("/fichiers/").path(file).toUriString();
		StringBuilder base64 = new StringBuilder("data:image/png;base64,");
		base64.append(java.util.Base64.getEncoder().encodeToString(p));
		return base64.toString(); // result;
	}

	public Path loadFileFromPath(String file) throws IOException {
		// byte[] p = Files.readAllBytes(Paths.get("fichiers/" + file));
		Path p = Paths.get("fichiers/" + file);

		/*StringBuilder base64 = new StringBuilder("data:image/png;base64,");
		base64.append(java.util.Base64.getEncoder().encodeToString(p));*/
		return p; // base64.toString();
	}

	/* =============================== Charger la photo du back-end pour afficher dans le front-end ============================== */
	public String loadPhoto(String file) throws IOException {
		String result = null;
		/*System.out.println("******************* Photo **************");
		System.out.println(file);*/
		if(file != null) {
			try {
				byte[] p = Files.readAllBytes(Paths.get("fichiers/" + file));

				StringBuilder base64 = new StringBuilder("data:image/png;base64,");
				base64.append(java.util.Base64.getEncoder().encodeToString(p));
				result = base64.toString();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}
		return result;
	}

	/* =============================== Charger la photo du back-end pour afficher dans le front-end ============================== */
	public String loadPhotoInGet(String file) throws IOException {
		String result = null;
		ArrayList<String> files = new ArrayList<>();
		if(file != null) {
			try {
				List<String> fileList = new ArrayList<String>(Arrays.asList(file));
				for (String f : fileList) {
					byte[] p = Files.readAllBytes(Paths.get("fichiers/" + f));
					StringBuilder base64 = new StringBuilder("data:image/png;base64,");
					base64.append(Base64.getEncoder().encodeToString(p));
					// result = base64.toString();
					files.add(base64.toString());
				}
				for(String r: files){

					result +=r;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/* =============================== Charger la photo du back-end pour afficher dans le front-end ============================== */
	public List<String> loadPhotoInList(List<String> file) throws IOException {
		// HashMap<String, String> toCompressed = null, toUncompressed;
		List<String> result = new ArrayList<>();
		/*System.out.println("******************* Photo **************");
		System.out.println(file);*/
		if(file != null) {
			try {
				// byte[] p = Files.readAllBytes(Paths.get("fichiers/"+s).toAbsolutePath());
					/*Path pt = Paths.get("fichiers/"+file);
					InputStream inputStream = new FileInputStream(new File(String.valueOf(pt)));
					if ( inputStream != null ) {
						InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
						BufferedReader in = new BufferedReader(inputStreamReader); // new FileReader(String.valueOf(pt)));
						// String encodedString = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(p); // getEncoder().withoutPadding().encodeToString(p); // .getMimeEncoder().encodeToString(p);

						String receiveString = "";
						StringBuilder stringBuilder = new StringBuilder(); // "data:image/png;base64,"

						while ( (receiveString = in.readLine()) != null ) {
							stringBuilder.append(receiveString);
							// stringBuilder.append(Base64.getEncoder().encodeToString(receiveString));
						}

						inputStream.close();

						result = stringBuilder.toString();
					}*/
        /*String zipFileName = file.concat(".zip");

        FileOutputStream fos = new FileOutputStream(zipFileName);
        ZipOutputStream zos = new ZipOutputStream(fos);

        zos.putNextEntry(new ZipEntry(file));

        byte[] bytes = Files.readAllBytes(Paths.get("fichiers/"+file));
        zos.write(bytes, 0, bytes.length);
        zos.closeEntry();
        zos.close();*/
				for (String f : file) {
					byte[] p = Files.readAllBytes(Paths.get("fichiers/" + f));
					StringBuilder base64 = new StringBuilder("data:image/png;base64,");
					base64.append(Base64.getEncoder().encodeToString(p));
					// result = base64.toString();
					result.add(base64.toString());
				}
				// byte[] p = Files.readAllBytes(Paths.get("fichiers/"+file)); // .toAbsolutePath());
				// String encodedString = org.apache.tomcat.util.codec.binary.Base64.encodeBase64String(p); // getEncoder().withoutPadding().encodeToString(p); // .getMimeEncoder().encodeToString(p);

			/*StringBuilder base64 = new StringBuilder("data:image/png;base64,");
			base64.append(Base64.getEncoder().encodeToString(p));*/
				// System.out.println(base64.toString());
			/*System.out.println("************* Fichier ****************");
			System.out.println(p);
			System.out.println(p.toString());
			System.out.println("*****************************");*/
				// GZIPOutputStream gos = new GZIPOutputStream(new FileOutputStream("fichiers/"+file+".zip"));
				// new FileOutputStream(target.toFile())));

				// gos.write(base64.toString().getBytes(StandardCharsets.UTF_8));

				// Image image = new ImageIcon("fichiers/"+file).getImage();
				// Image image = new ImageIcon(this.getClass().getResource("fichiers/"+file)).getImage();
				Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("fichiers/" + file));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/* =============================== Charger des photos du back-end pour afficher dans le front-end(Carte grise, Cnib et photo de l'engin) ============================== */
	public String loadCarteGrise(String file) throws IOException {
		ArrayList<String> files = new ArrayList<>();
		String result = null;

		try {
			if(file != null) {
				for (String r : substringIn2StringsFromBack(file)) {
					byte[] p = Files.readAllBytes(Paths.get("fichiers/" + r));

					StringBuilder base64 = new StringBuilder(); // "data:image/png;base64,");
					base64.append(Base64.getEncoder().encodeToString(p));
					files.add(base64.toString());
				}
				result = org.springframework.util.StringUtils.collectionToCommaDelimitedString(files);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void supprimerFichierDuPath(String file) throws IOException {
		Files.delete(Paths.get("fichiers/" +file)); //on supprime l'ancienne
	}

	/* ============================================================= */
	public String loadCnibAndEngin(String file) throws IOException {
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> files = new ArrayList<>();
		String result = null;
		try {
			if(file != null) {
				res = substringIn3StringsFromBack(file);

				for (String r : res) {
					byte[] p = Files.readAllBytes(Paths.get("fichiers/" + r));

					StringBuilder base64 = new StringBuilder(); // ("data:image/png;base64,");
					base64.append(Base64.getEncoder().encodeToString(p));
					files.add(base64.toString());
				}
				result = org.springframework.util.StringUtils.collectionToCommaDelimitedString(files);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String loadCnibAndEnginInList(String file) throws IOException {
		String result = null;
		List<String> fileList = new ArrayList<String>(Arrays.asList(file));
		ArrayList<String> res = new ArrayList<>();
		ArrayList<String> files = new ArrayList<>();
		if(file != null) {
			try {
				for (String f : fileList) {
					res = substringIn3StringsFromBack(f);

					for (String r : res) {
						byte[] p = Files.readAllBytes(Paths.get("fichiers/" + r));

						StringBuilder base64 = new StringBuilder();
						base64.append(Base64.getEncoder().encodeToString(p));
						files.add(base64.toString());
					}
					result = org.springframework.util.StringUtils.collectionToCommaDelimitedString(files);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/*======================= Délimiter par une virgule des strings base64(images) dans un tableau depuis le front-end(carte grise, permis, assurance) et les stocker ====================*/
	public String convertAndSplitCarteGriseAssurPermis(String sFile) throws IOException {
		ArrayList<String> sR = new ArrayList<String>();
		ArrayList<String> sF = new ArrayList<String>();
		sR = substringIn2StringsFromFront(sFile);
		String res = null;
		String result = null;
		for (String s : sR) {
			res = StoreImage(s);
			sF.add(res);
		}
		return result = org.springframework.util.StringUtils.collectionToCommaDelimitedString(sF); //cette classe permet de convertir le tableau en String en les délimitant avec des virgules
	}

	/*===================== Délimiter par une virgule des strings base64(images) dans un tableau depuis le front-end(CNIB, photo engin) et les stocker  ===================*/
	public String convertAndSplitCnibAndEngin(String sFile) throws IOException {
		ArrayList<String> sR = new ArrayList<String>();
		ArrayList<String> sF = new ArrayList<String>();
		sR = substringIn3StringsFromFront(sFile);
		String res = null;
		String result = null;
		for (String s : sR) {
		    res = StoreImage(s);
			sF.add(res);
		}
		return result = org.springframework.util.StringUtils.collectionToCommaDelimitedString(sF); //cette classe permet de convertir le tableau en String en les délimitant avec des virgules
	}

    /*=============================== Séparer deux strings par une virgule dans un tableau du front-end ============================*/
    public ArrayList<String> substringIn2StringsFromFront(String s) {
		String sR = null;
		String sR2 = null;
		ArrayList<String> sResult = new ArrayList<String>();
		sR = StringUtils.substringBefore(s, "-");
		sR2 = StringUtils.substringAfterLast(s, "-");
		String[] strs = new String[]{ sR, sR2};
		for (String sF : strs)
			sResult.add(sF);
        return sResult;
    }

    /*=============================== Séparer trois strings par une virgule dans un tableau du front-end ============================*/
    public ArrayList<String> substringIn3StringsFromFront(String sFile) {
        String sR = null;
        String sR2 = null;
        String sR3 = null;
		String[] strs;
        ArrayList<String> sResult = new ArrayList<String>();
        if(StringUtils.countMatches(sFile, "-")>1) {
			sR = StringUtils.substringBefore(sFile, "-");
			sR2 = StringUtils.substringBetween(sFile, "-");
			sR3 = StringUtils.substringAfterLast(sFile, "-");
			strs = new String[]{ sR, sR2, sR3};
		}
        else{
			sR = StringUtils.substringBefore(sFile, "-");
			sR2 = StringUtils.substringAfterLast(sFile, "-");
			strs = new String[]{ sR, sR2};
		}

            for (String s : strs)
                sResult.add(s);

        return sResult;
    }

	public String substringIn3StringsFromFrontTest(String sFile) {
		String sR = null;
		String sR2 = null;
		String sR3 = null;
		String[] strs;
		String sResult = null;
		if(StringUtils.countMatches(sFile, "-")>1) {
			sR = StringUtils.substringBefore(sFile, "-");
			sR2 = StringUtils.substringBetween(sFile, "-");
			sR3 = StringUtils.substringAfterLast(sFile, "-");
			strs = new String[]{ sR, sR2, sR3};
			sResult = "Le mot contient ce caractère 2 fois";
		}
		else{
			sR = StringUtils.substringBefore(sFile, "-");
			sR2 = StringUtils.substringAfterLast(sFile, "-");
			strs = new String[]{ sR, sR2};
			sResult = "Le mot contient ce caractère 1 fois";
		}

		return sResult;
	}

	/*=============================== Séparer deux fichiers par une virgule dans un tableau du back-end ============================*/
	public ArrayList<String> substringIn2StringsFromBack(String sFile) {
		String sR = null;
		String sR2 = null;
		ArrayList<String> sResult = new ArrayList<String>();
		sR = StringUtils.substringBefore(sFile, ",");
		sR2 = StringUtils.substringAfterLast(sFile, ",");
		String[] strs = new String[]{ sR, sR2};
		for (String s : strs)
			sResult.add(s);

		return sResult;
	}

	/*=============================== Séparer trois fichiers par une virgule dans un tableau du back-end ============================*/
	public ArrayList<String> substringIn3StringsFromBack(String sFile) {
		String sR = null;
		String sR2 = null;
		String sR3 = null;
		ArrayList<String> sResult = new ArrayList<String>();
		sR = StringUtils.substringBefore(sFile, ",");
		sR2 = StringUtils.substringBetween(sFile, ",");
		sR3 = StringUtils.substringAfterLast(sFile, ",");
		String[] strs = new String[]{ sR, sR2, sR3};
		for (String s : strs)
			sResult.add(s);

		return sResult;
	}

	/* =================== Conversion d'un fichier de base64 en image ================== */
	public String StoreImage(String im) throws IOException {
		String image = null;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		try {
			// if(im != null && )
			String parts = im.split(",")[1];
			/*System.out.println("********** Découpage ************");
			System.out.println(parts);
			System.out.println("**********************");*/
			byte[] imageString = DatatypeConverter.parseBase64Binary(parts);

			BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageString));
			// write the image to a file
			File outputfile = new File("/Users/geraldine/Documents/BAFA-TECH/ChristaB/fichiers/"+now+".jpeg"); // strDate+".jpeg");
			ImageIO.write(img, "jpeg", outputfile);
			image = now+".jpeg"; // strDate+".jpeg";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

/********************* Autre fonction **********************/
	public String StoreImageTest(String file) throws IOException {

		String image = null;
		/*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		// tokenize the data
		String[] parts = file.split(",");
		String imageString = parts[1];

		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();

		// write the image to a file
		*//*File outputfile = new File("image.png");
		ImageIO.write(image, "png", outputfile);*//*
		File outputfile = new File("/Users/geraldine/Documents/BAFA-TECH/ChristaB/fichiers/"+now+".jpeg");
		ImageIO.write(image, "png", outputfile);*/
		return image;
	}

	/* =================== Conversion des icônes de base64 en image ================== */
	public String StoreIcone(String im) throws IOException {
		String image;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();

		String parts = im.split(",")[1];
		byte[] imageString = DatatypeConverter.parseBase64Binary(parts);

		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageString));
		// write the image to a file
		File outputfile = new File("/Users/geraldine/Documents/BAFA-TECH/ChristaB/fichiers/"+now+".png");
		ImageIO.write(img, "png", outputfile);
		image = now+".png";
		return image;
	}

    /* ================== Convertir une liste en tableau ============== */
	public String[] listStringToStringsArray(List<String> a) {
		String [] array = a.toArray(new String[0]);
		return array;
	}
	
	public String ConcatString(String s) {
		return s.concat(";");
	}

	/* ======================== Convertir un string en date ========================== */
	public Date StringToDate(String s) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse(s);
		return d;
	}
	
	public String getAdressIp() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // permet de récupérer l'adresse ip locale
		String ipaddress = addr.getHostAddress(); // récupère l'adresse ip de la machine
		return ipaddress;
	}

	final HttpServletRequest request = new HttpServletRequest() {

		public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
				throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		public AsyncContext startAsync() throws IllegalStateException {
			// TODO Auto-generated method stub
			return null;
		}

		public void setCharacterEncoding(String env) throws UnsupportedEncodingException {
			// TODO Auto-generated method stub

		}

		public void setAttribute(String name, Object o) {
			// TODO Auto-generated method stub

		}

		public void removeAttribute(String name) {
			// TODO Auto-generated method stub

		}

		public boolean isSecure() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isAsyncSupported() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isAsyncStarted() {
			// TODO Auto-generated method stub
			return false;
		}

		public ServletContext getServletContext() {
			// TODO Auto-generated method stub
			return null;
		}

		public int getServerPort() {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getServerName() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getScheme() {
			// TODO Auto-generated method stub
			return null;
		}

		public RequestDispatcher getRequestDispatcher(String path) {
			// TODO Auto-generated method stub
			return null;
		}

		public int getRemotePort() {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getRemoteHost() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRemoteAddr() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRealPath(String path) {
			// TODO Auto-generated method stub
			return null;
		}

		public BufferedReader getReader() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		public String getProtocol() {
			// TODO Auto-generated method stub
			return null;
		}

		public String[] getParameterValues(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public Enumeration<String> getParameterNames() {
			// TODO Auto-generated method stub
			return null;
		}

		public Map<String, String[]> getParameterMap() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getParameter(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public Enumeration<Locale> getLocales() {
			// TODO Auto-generated method stub
			return null;
		}

		public Locale getLocale() {
			// TODO Auto-generated method stub
			return null;
		}

		public int getLocalPort() {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getLocalName() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getLocalAddr() {
			// TODO Auto-generated method stub
			return null;
		}

		public ServletInputStream getInputStream() throws IOException {
			// TODO Auto-generated method stub
			return null;
		}

		public DispatcherType getDispatcherType() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getContentType() {
			// TODO Auto-generated method stub
			return null;
		}

		public long getContentLengthLong() {
			// TODO Auto-generated method stub
			return 0;
		}

		public int getContentLength() {
			// TODO Auto-generated method stub
			return 0;
		}

		public String getCharacterEncoding() {
			// TODO Auto-generated method stub
			return null;
		}

		public Enumeration<String> getAttributeNames() {
			// TODO Auto-generated method stub
			return null;
		}

		public Object getAttribute(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public AsyncContext getAsyncContext() {
			// TODO Auto-generated method stub
			return null;
		}

		public <T extends HttpUpgradeHandler> T upgrade(Class<T> handlerClass) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

		public void logout() throws ServletException {
			// TODO Auto-generated method stub

		}

		public void login(String username, String password) throws ServletException {
			// TODO Auto-generated method stub

		}

		public boolean isUserInRole(String role) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRequestedSessionIdValid() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRequestedSessionIdFromUrl() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRequestedSessionIdFromURL() {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean isRequestedSessionIdFromCookie() {
			// TODO Auto-generated method stub
			return false;
		}

		public Principal getUserPrincipal() {
			// TODO Auto-generated method stub
			return null;
		}

		public HttpSession getSession(boolean create) {
			// TODO Auto-generated method stub
			return null;
		}

		public HttpSession getSession() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getServletPath() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRequestedSessionId() {
			// TODO Auto-generated method stub
			return null;
		}

		public StringBuffer getRequestURL() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRequestURI() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getRemoteUser() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getQueryString() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getPathTranslated() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getPathInfo() {
			// TODO Auto-generated method stub
			return null;
		}

		public Collection<Part> getParts() throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

		public Part getPart(String name) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return null;
		}

		public String getMethod() {
			// TODO Auto-generated method stub
			return null;
		}

		public int getIntHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

		public Enumeration<String> getHeaders(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public Enumeration<String> getHeaderNames() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getHeader(String name) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getDateHeader(String name) {
			// TODO Auto-generated method stub
			return 0;
		}

		public Cookie[] getCookies() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getContextPath() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getAuthType() {
			// TODO Auto-generated method stub
			return null;
		}

		public String changeSessionId() {
			// TODO Auto-generated method stub
			return null;
		}

		public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
			// TODO Auto-generated method stub
			return false;
		}
	};

	public String getUserAgent() {
		// Get an UserAgentStringParser and analyze the requesting client
		UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		//System.out.println(userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion());
		// UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
		OperatingSystem agent = userAgent.getOperatingSystem(); // OS
		//System.out.println("User-Agent " + userAgent);
		Browser browser = userAgent.getBrowser(); // Browser
		Version browserVersion = userAgent.getBrowserVersion(); // version
		String browserName = userAgent.getBrowser().getName(); // Browser Name
		String osName = agent.getName();
		// return agent.getDeviceType().getName();
		return browserName;
	}
	
}
