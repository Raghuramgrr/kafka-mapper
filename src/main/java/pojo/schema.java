package pojo;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import java.beans.Visibility;

@ToString
@Slf4j
@JsonAutoDetect(fieldVisibility = Visibility.ANY,getterVisibility=Visibility.NONE,setterVisibility=Visibility.NONE)
public class schema {
    public static final String f1="F1";
    public static final String f2="F2";
    public static final String f3="F3";
    public static final String f4="F4";
    public static final String f5="F5";


}
