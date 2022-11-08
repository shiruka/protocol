package io.github.shiruka.protocol.common;

import com.google.common.base.Preconditions;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.util.function.Predicate;
import org.jetbrains.annotations.NotNull;

/**
 * an interface to determine type parameter matcher.
 */
public interface TypeParameterMatcher extends Predicate<Object> {

  /**
   * finds the class internally.
   *
   * @param object the object to find.
   * @param parametrizedSuperclass the parametrized superclass to find.
   * @param typeParamName the type param name to find.
   *
   * @return found class.
   */
  @NotNull
  static Class<?> find(
    @NotNull final Object object,
    @NotNull Class<?> parametrizedSuperclass,
    @NotNull String typeParamName
  ) {
    final var thisClass = object.getClass();
    var currentClass = thisClass;
    for (; ; ) {
      if (currentClass.getSuperclass() != parametrizedSuperclass) {
        currentClass = currentClass.getSuperclass();
        Preconditions.checkNotNull(
          currentClass,
          "cannot determine the type of the type parameter '%s': %s",
          typeParamName,
          thisClass
        );
        continue;
      }
      var typeParamIndex = -1;
      final var typeParams = currentClass.getSuperclass().getTypeParameters();
      for (var i = 0; i < typeParams.length; i++) {
        if (typeParamName.equals(typeParams[i].getName())) {
          typeParamIndex = i;
          break;
        }
      }
      Preconditions.checkState(
        typeParamIndex >= 0,
        "unknown type parameter '%s': %s",
        typeParamName,
        parametrizedSuperclass
      );
      final var genericSuperType = currentClass.getGenericSuperclass();
      if (!(genericSuperType instanceof ParameterizedType parameterizedType)) {
        return Object.class;
      }
      final var actualTypeParams = parameterizedType.getActualTypeArguments();
      var actualTypeParam = actualTypeParams[typeParamIndex];
      if (actualTypeParam instanceof ParameterizedType type) {
        actualTypeParam = type.getRawType();
      }
      if (actualTypeParam instanceof Class<?> cls) {
        return cls;
      }
      if (actualTypeParam instanceof GenericArrayType arrayType) {
        var componentType = arrayType.getGenericComponentType();
        if (componentType instanceof ParameterizedType type) {
          componentType = type.getRawType();
        }
        if (componentType instanceof Class<?> type) {
          return Array.newInstance(type, 0).getClass();
        }
      }
      if (actualTypeParam instanceof TypeVariable<?> v) {
        if (!(v.getGenericDeclaration() instanceof Class)) {
          return Object.class;
        }
        currentClass = thisClass;
        parametrizedSuperclass = (Class<?>) v.getGenericDeclaration();
        typeParamName = v.getName();
        if (parametrizedSuperclass.isAssignableFrom(thisClass)) {
          continue;
        }
        return Object.class;
      }
      throw new IllegalStateException(
        "cannot determine the type of the type parameter '" +
          typeParamName +
          "': " +
          thisClass
      );
    }
  }

  /**
   * finds the matcher.
   *
   * @param object the object to find.
   * @param parametrizedSuperclass the parametrized superclass to find.
   * @param typeParamName the type param name to find.
   *
   * @return matcher.
   */
  @NotNull
  static TypeParameterMatcher of(
    @NotNull final Object object,
    @NotNull final Class<?> parametrizedSuperclass,
    @NotNull final String typeParamName
  ) {
    return TypeParameterMatcher.of(
      TypeParameterMatcher.find(object, parametrizedSuperclass, typeParamName)
    );
  }

  /**
   * gets the matcher.
   *
   * @param parameterType the parameter type to get.
   *
   * @return matcher.
   */
  @NotNull
  private static TypeParameterMatcher of(final Class<?> parameterType) {
    return parameterType == Object.class
      ? msg -> true
      : parameterType::isInstance;
  }
}
