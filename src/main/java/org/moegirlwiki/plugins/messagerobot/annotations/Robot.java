package org.moegirlwiki.plugins.messagerobot.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * put this annotation on your {@link org.moegirlwiki.plugins.messagerobot.interfaces.AbstractRobot} class <br/>
 * in the {@link org.moegirlwiki.plugins.messagerobot.robots}package<br/>
 * any robot has this annotation in this package will be execute by the main progress
 * @author xuechong
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)  
@Documented 
public @interface Robot {}
