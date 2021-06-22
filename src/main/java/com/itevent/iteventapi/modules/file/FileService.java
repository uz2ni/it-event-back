package com.itevent.iteventapi.modules.file;

import com.itevent.iteventapi.modules.file.dto.FileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
            // file명 변환
            String origFileName = file.getOriginalFilename(); //xxx.jpg
            String onlyFileName = origFileName.substring(0, origFileName.indexOf(".")); // xxx
            String extension = origFileName.substring(origFileName.indexOf(".")); // .jpg

            String fileName = onlyFileName + "_" + getCurrentDayTime() + extension; // xxx_20210622-22-23-10.jpg
            String folderName = file.getName();

            String savePath = dataDir + "\\uploadFiles\\" + folderName;

            // 디렉토리 존재 체크 및 생성
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

    private String getCurrentDayTime(){
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yyyyMMdd-HH-mm-ss", Locale.KOREA);
        return dayTime.format(new Date(time));
    }

}
