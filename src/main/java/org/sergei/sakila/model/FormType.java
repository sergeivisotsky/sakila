package org.sergei.sakila.model;

import com.google.errorprone.annotations.Immutable;

/**
 * @author Sergei Visotsky
 */
@Immutable
public class FormType {
    private Integer numberOfElements;
    private String formDescription;

    private FormType() {
    }

    public Integer getNumberOfElements() {
        return numberOfElements;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public static final class FormTypeBuilder {
        private Integer numberOfElements;
        private String formDescription;

        public FormTypeBuilder() {
        }

        public static FormTypeBuilder aFormType() {
            return new FormTypeBuilder();
        }

        public FormTypeBuilder withNumberOfElements(Integer numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public FormTypeBuilder withFormDescription(String formDescription) {
            this.formDescription = formDescription;
            return this;
        }

        public FormType build() {
            FormType formType = new FormType();
            formType.formDescription = this.formDescription;
            formType.numberOfElements = this.numberOfElements;
            return formType;
        }
    }
}
