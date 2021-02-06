package com.eremin.staff.other;

import com.eremin.staff.mapdata.UserData;

import java.util.Objects;

public class TextValidation {
    /**
     * Проверка данных на пустые значения
     * @param data Входные данные польховатея
     * @return true если данные есть false если какие либо поля отсутствуют
     */
    public boolean notEmpty(UserData data){
        if(Objects.equals(data.getId(), "") || data.getId() == null){
            data.setId(String.valueOf(System.currentTimeMillis()));
        }
        return !Objects.equals(data.getId(), "")
                && !Objects.equals(data.getLastName(), "")
                && !Objects.equals(data.getFirstName(), "")
                && !Objects.equals(data.getFatherName(), "")
                && !Objects.equals(data.getPosition(), "");
    }

    /**
     * Подготовка данных перед записью в БД
     * @param data входные данные
     * @return вернет UserData удалив пробелы
     */
    public UserData removeSpaces(UserData data){
        data.setFirstName(ucFirst(strTrim(Objects.requireNonNull(data.getFirstName()))));
        data.setLastName(ucFirst(strTrim(Objects.requireNonNull(data.getLastName()))));
        data.setFatherName(ucFirst(strTrim(Objects.requireNonNull(data.getFatherName()))));
        data.setPosition(ucFirst(Objects.requireNonNull(data.getPosition()).trim()));
        return data;
    }

    /**
     * Делает первый символ строки верхним регистром
     * а остальные символы нижним регистром
     * @param data строка
     * @return исправлнную строку
     */
    private String ucFirst(String data){
        return data.substring(0,1).toUpperCase() + data.substring(1).toLowerCase();
    }

    private String strTrim(String data){
        return data.replaceAll("\\s+","");
    }
}
