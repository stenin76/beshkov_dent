package beshkov_dent.model.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MessageAddDto {

    @NotBlank(message = "Полето е задължително!")
    private String fullName;

    @NotBlank(message = "Полето е задължително!")
    private String number;

    @Email(message = "Невалиден E-mail")
    private String email;

    @NotBlank(message = "Полето е задължително!")
    @Size(max = 500, message = "Максималната дължина на полето е 500 символа!")
    private String content;

    @AssertTrue(message = "Моля потвърдете ,че сте се запознали с условията за ползване!")
    private boolean terms;


    public MessageAddDto() {}



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isTerms() {
        return terms;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }
}
