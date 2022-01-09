package org.sid.metier.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploaderService {

	public void uploadFile(MultipartFile file);
} 
