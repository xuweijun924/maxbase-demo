package com.maxnerva.maxbase.demo.common.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author Shengxiang Xu
 * @date 3/17/2022
 */
@Data
@Slf4j
@Validated
@Component
@RefreshScope
public class LoggingLevel {

    private static final String LOGGING_LEVEL_PREFIX = "logging.level.";
    private static final String ROOT = "root";
    private static final String ROOT_PACKAGE = "com.maxnerva.maxbase.demo";

    @NotBlank
    @Value("${" + LOGGING_LEVEL_PREFIX + ROOT + "}")
    private String rootLevel;
    @NotBlank
    @Value("${" + LOGGING_LEVEL_PREFIX + ROOT_PACKAGE + "}")
    private String rootPackageLevel;

    @EventListener
    public void onRefreshScopeRefreshed(final RefreshScopeRefreshedEvent event) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.getLogger(ROOT).setLevel(Level.toLevel(rootLevel));
        loggerContext.getLogger(ROOT_PACKAGE).setLevel(Level.toLevel(rootPackageLevel));

        String format = "The log configuration is changed, rootLevel: %s, packageLevel: %s";
        String msg = String.format(format, rootLevel, rootPackageLevel);
        if (log.isDebugEnabled() && loggerContext.getLogger(ROOT).getLevel().equals(Level.DEBUG)) {
            log.debug(msg);
            return;
        }
        log.info(msg);
    }

}
