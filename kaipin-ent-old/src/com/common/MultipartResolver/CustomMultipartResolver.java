package com.common.MultipartResolver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import org.apache.commons.fileupload.FileItemFactory;

import org.apache.commons.fileupload.FileUpload;

import org.apache.commons.fileupload.FileUploadBase;

import org.apache.commons.fileupload.FileUploadException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.springframework.web.multipart.MaxUploadSizeExceededException;

import org.springframework.web.multipart.MultipartException;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 
 * 功能描述：文件上传Resolver <br>
 * 
 */

public class CustomMultipartResolver extends CommonsMultipartResolver {

	private HttpServletRequest request;

	protected FileUpload newFileUpload(FileItemFactory fileItemFactory) {

		ServletFileUpload upload = new ServletFileUpload(fileItemFactory);

		// upload.setSizeMax(-1);

		if (request != null) {

			MyProgressListener progressListener = new MyProgressListener(request);

			upload.setProgressListener(progressListener);

		}

		return upload;

	}

	public MultipartHttpServletRequest resolveMultipart(HttpServletRequest request) throws MultipartException {

		// 获取到request,要用到session

		this.request = request;

		return super.resolveMultipart(request);

	}

	@Override

	public MultipartParsingResult parseRequest(HttpServletRequest request) throws MultipartException {

		HttpSession session = request.getSession();

		String encoding = determineEncoding(request);

		FileUpload fileUpload = prepareFileUpload(encoding);

		// 设置监听器

		MyProgressListener progressListener = new MyProgressListener(request);

		fileUpload.setProgressListener(progressListener);

		try {

			List<FileItem> fileItems = ((ServletFileUpload) fileUpload).parseRequest(request);

			return parseFileItems(fileItems, encoding);

		} catch (FileUploadBase.SizeLimitExceededException slex) {

			throw new MaxUploadSizeExceededException(fileUpload.getSizeMax(), slex);

		} catch (FileUploadException fuex) {

			throw new MultipartException("Could not parse multipart servlet request", fuex);

		}

	}

}
