# Исправление ViewModel после перехода на Lifecycle

Я исправил ошибки, возникшие при переходе на библиотеку `androidx.lifecycle` и использовании `viewModelScope`.

## Что было сделано

### 1. Очистка BaseViewModel (Shared)
В файле [BaseViewModel.kt](file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/presentation/BaseViewModel.kt) я удалил ручной вызов `scope.cancel()`.
- **Почему**: `viewModelScope` из библиотеки Lifecycle отменяется автоматически, когда ViewModel очищается. Ручная отмена больше не требуется.

### 2. Исправление NewsViewModel (Android)
В файле [NewsViewModel.kt](file:///C:/Home/work/KMP/androidApp/src/main/kotlin/com/example/testkmpapp/presentation/news/NewsViewModel.kt) устранены следующие проблемы:
- **Удалена ошибочная инициализация**: Удалена переменная `useCase`, которая создавалась через `NewsUseCase()` без параметров (что приводило к ошибке компиляции). Теперь используется `newsUseCase` из `DI`.
- **Исправлен тип данных**: В методе `loadNews` теперь правильно извлекается список статей из объекта `NewsItemsList` (`it?.articles.orEmpty()`) перед обновлением потока `_news`.
- **Использование viewModelScope**: Методы `fetchData` и `loadNews` теперь единообразно используют встроенный `viewModelScope`.

## Результаты
- Ошибки компиляции в обоих модулях устранены.
- Код теперь полностью соответствует правилам работы с современным `androidx.lifecycle`.

render_diffs(file:///C:/Home/work/KMP/shared/src/commonMain/kotlin/com/example/testkmpapp/presentation/BaseViewModel.kt)
render_diffs(file:///C:/Home/work/KMP/androidApp/src/main/kotlin/com/example/testkmpapp/presentation/news/NewsViewModel.kt)
