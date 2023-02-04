package com.clapcode.confluence.plugin.macro;

import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.renderer.radeox.macros.MacroUtils;
import com.atlassian.confluence.setup.settings.SettingsManager;
import com.atlassian.confluence.util.velocity.VelocityUtils;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.plugin.webresource.WebResourceManager;
import com.atlassian.sal.api.ApplicationProperties;
import com.atlassian.webresource.api.assembler.PageBuilderService;
import com.google.common.collect.Maps;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorld implements Macro {

  @ComponentImport
  private SettingsManager settingsManager;

  @ComponentImport
  private ApplicationProperties applicationProperties;

  @ComponentImport
  private WebResourceManager webResourceManager;

  @ComponentImport
  private PageBuilderService pageBuilderService;

  @Autowired
  public HelloWorld(SettingsManager settingsManager, ApplicationProperties applicationProperties,
      WebResourceManager webResourceManager, PageBuilderService pageBuilderService) {

    this.settingsManager = settingsManager;
    this.applicationProperties = applicationProperties;
    this.webResourceManager = webResourceManager;
    this.pageBuilderService = pageBuilderService;
  }

  @Override
  public String execute(Map<String, String> map, String s, com.atlassian.confluence.content.render.xhtml.ConversionContext conversionContext) throws MacroExecutionException {

    /*
    * Approach - 1
    * */
    pageBuilderService.assembler().resources().requireWebResource("com.clapcode.confluence.plugin.hello-macro:hello-macro-resources");

    String output = "<div class =\"helloworld\">";
    output = output + "<div class = \"" + map.get("Color") + "\">";
    if (map.get("Name") != null) {
      output = output + ("<h1>Hello " + map.get("Name") + "!</h1>");
    } else {
      output = output + "<h1>Hello World!<h1>";
    }
    output = output + "</div>" + "</div>";
    return output;

    /*
    * Approach - 2
    * */
//    webResourceManager.requireResource("com.clapcode.confluence.plugin.hello-macro:hello-macro-resources");
//
//    Map contextMap = MacroUtils.defaultVelocityContext();
//    contextMap.put("name", map.get("Name")!=null?map.get("Name"):"Hey there, shall I call you Rocky");
//    contextMap.put("color", map.get("Color")!=null?map.get("Color"):"Pink");
//
//    return VelocityUtils.getRenderedTemplate("templates/macro-body.vm", contextMap);
  }

  public BodyType getBodyType() { return BodyType.NONE; }

    public OutputType getOutputType() { return OutputType.BLOCK; }
}
