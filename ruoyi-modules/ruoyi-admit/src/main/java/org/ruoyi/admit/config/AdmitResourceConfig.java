package org.ruoyi.admit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 招生录取模块静态资源配置
 * 用于配置考生照片等静态资源的访问路径
 *
 * @author system
 */
@Configuration
public class AdmitResourceConfig implements WebMvcConfigurer {

    /**
     * 考生照片存储路径
     */
    private static final String PHOTO_PATH = "file:D:/招生数据/高考照片/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射 /admit/photos/** 到本地照片目录
        registry.addResourceHandler("/admit/photos/**")
                .addResourceLocations(PHOTO_PATH);
    }
}
