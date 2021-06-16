package com.itevent.iteventapi.modules.file.dto;

import com.itevent.iteventapi.common.utils.ModelMapperUtils;
import com.itevent.iteventapi.modules.event.dto.EventResDto;
import com.itevent.iteventapi.modules.file.File;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class FileDto {
    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public static FileDto of(File file) {
        FileDto fileDto = ModelMapperUtils.getModelMapper().map(file, FileDto.class);
        return fileDto;
    }
}