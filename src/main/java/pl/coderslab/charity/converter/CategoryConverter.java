package pl.coderslab.charity.converter;

import org.springframework.core.convert.converter.Converter;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;

public class CategoryConverter implements Converter<Long, Category> {

    private CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(Long id) {
        return categoryRepository.findById(id).get();
    }
}
