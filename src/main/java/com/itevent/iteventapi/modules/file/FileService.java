package com.itevent.iteventapi.modules.file;

import com.itevent.iteventapi.modules.file.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;

    @Value("${app.dataDir}")
    private String dataDir;

    public Long saveFile(FileDto dto) {
        return fileRepository.save(File.of(dto)).getId();
    }

    public FileDto getFile(Long id) {
        File file = fileRepository.findById(id).get();
        FileDto fileDto = FileDto.of(file);

        return fileDto;
    }

    public Long upload(MultipartFile file) throws IOException {
        try {
            String origFileName = file.getOriginalFilename();
            String fileName = origFileName; // TODO: 업로드용 파일명 변환
            String folderName = file.getName();

            String savePath = dataDir + "\\uploadFiles\\" + folderName;

            if (!new java.io.File(savePath).exists()) {
                try {
                    new java.io.File(savePath).mkdirs();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String filePath = savePath + "\\" + fileName;
            file.transferTo(new java.io.File(filePath));

            // DB 저장
            FileDto fileDto = FileDto.builder()
                                    .origFilename(origFileName)
                                    .filename(fileName)
                                    .filePath(filePath).build();

            Long fileId = saveFile(fileDto);

            return fileId;

        }catch (IOException e) {
            throw new IOException(e);
        }

    }

}
