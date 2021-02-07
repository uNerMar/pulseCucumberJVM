package com.pwc;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
// TODO how to generate html report 
@CucumberOptions(plugin = { "pretty", "html:target/cucumber" })
public class RunCucumberTest {

}
