package com.clapcode.confluence.plugin.macro;

import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import java.util.Map;

public class HelloWorld implements Macro {

  @Override
  public String execute(Map<String, String> map, String s,
      com.atlassian.confluence.content.render.xhtml.ConversionContext conversionContext)
      throws MacroExecutionException {

    return "<h1>Hello World</h1>";
  }

  public BodyType getBodyType() { return BodyType.NONE; }

    public OutputType getOutputType() { return OutputType.BLOCK; }
}
