package com.itevent.iteventapi.modules.file;

import com.itevent.iteventapi.modules.file.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    public Long saveFile(FileDto dto) {
        return fileRepository.save(File.of(dto)).getId();
    }

    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();
        FileDto fileDto = FileDto.of(file);

        return fileDto;
    }

    public void upload(MultipartFile file) {

        String origFilename = file.getOriginalFilename();
        String filename = file.getName();

        // TODO: 지정 위치에 파일 저장
        // TODO: 저장되는 폴더 없을 시 폴더 생성

    }

}
