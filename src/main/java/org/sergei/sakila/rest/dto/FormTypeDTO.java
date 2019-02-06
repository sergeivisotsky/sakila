package org.sergei.sakila.rest.dto;

import com.google.errorprone.annotations.Immutable;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class FormTypeDTO {
    private Integer numberOfElements;
    private String formDescription;

    private FormTypeDTO() {
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public static final class FormTypeDTOBuilder {
        private Integer numberOfElements;
        private String formDescription;

        private FormTypeDTOBuilder() {
        }

        public static FormTypeDTOBuilder aFormTypeDTO() {
            return new FormTypeDTOBuilder();
        }

        public FormTypeDTOBuilder withNumberOfElements(Integer numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public FormTypeDTOBuilder withFormDescription(String formDescription) {
            this.formDescription = formDescription;
            return this;
        }

        public FormTypeDTO build() {
            FormTypeDTO formTypeDTO = new FormTypeDTO();
            formTypeDTO.numberOfElements = this.numberOfElements;
            formTypeDTO.formDescription = this.formDescription;
            return formTypeDTO;
        }
    }
}
