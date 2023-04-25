package com.kpodlaska.encyptionDecryption.production;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnicodeCryptoMethodTest {
    @Test
    public void analyzeUnicodeDecryption_singleCharKey1_DecodedSingleChar() {
        UnicodeCryptoMethod unicodeCryptoMethod = new UnicodeCryptoMethod();
        String result = unicodeCryptoMethod.decrypt("b",1);
        Assertions.assertEquals("a", result);
    }
    @Test
    public void analyzeUnicodeEncryption_singleUpperCaseWordKey10_DecodedSingleUpperCaseWord() {
        UnicodeCryptoMethod unicodeCryptoMethod = new UnicodeCryptoMethod();
        String result = unicodeCryptoMethod.encrypt("KAROLINA",10);
        Assertions.assertEquals("UK\\YVSXK", result);
    }
    @Test
    public void analyzeUnicodeEncryption_twoLowerCaseWordsKey5_DecodedSingleLowerCaseWord() {
        UnicodeCryptoMethod unicodeCryptoMethod = new UnicodeCryptoMethod();
        String result = unicodeCryptoMethod.decrypt("karolina podlaska",5);
        Assertions.assertEquals("f\\mjgdi\\\u001Bkj_g\\nf\\", result);
    }

}