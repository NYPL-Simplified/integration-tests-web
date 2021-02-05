package transformers;

import com.google.inject.Inject;
import io.cucumber.java.DataTableType;
import models.BookLabelsVisibility;

import java.util.Map;

public class CatalogTransformers {
    @Inject
    public CatalogTransformers() {

    }

    @DataTableType
    public BookLabelsVisibility getBookLabelsVisibilityModel(
            Map<String, String> entry) {
        return BookLabelsVisibility.createBookLabelsVisibilityModel(entry);
    }
}
