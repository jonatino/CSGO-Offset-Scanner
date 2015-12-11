package org.abendigo.misc;

import net.jodah.typetools.TypeResolver;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

public final class Prefs {

	private static File SETTINGS = new File("config.cfg");
	private static PropertiesConfiguration config;

	public static void load() {
		try {
			config = new PropertiesConfiguration(SETTINGS);
			config.setAutoSave(true);
		} catch (ConfigurationException e) {
			SETTINGS.delete();
			load();
		}
	}

	public static class Example<T> {
		public Class<T> type() {
			return (Class<T>) TypeResolver.resolveRawArgument(Example.class, getClass());
		}
	}

	public static <T> T wantsum(String key) {
		return (T) osama(key).get();
	}

	public static <T> Key<T> osama(String key) {
		return new Key<>(key);
	}

	public static class Key<T> {
		public final Class<T> type = (Class<T>) TypeResolver.resolveRawArgument(Key.class, getClass());
		public final String key;

		public Key(String key) {
			this.key = key;
		}

		public T get() {
			Object def = false;
			System.out.println("Type: " + type);
			if (Boolean.class.equals(type)) def = true;
			return gett(key, def);
		}
	}

	/*public static void main(String[] args) {
		System.out.println(new Example<String>() {}.type());
		if (get("Hello")) {
			System.out.println("Canadaoo");
		}
	}

	public static <T> T get(String key) {
		System.out.println("Hello: " + new Example<T>() {}.type());
		return get(() -> key);
	}*/

	public static <T> T get(String key, T defaultValue) {
		return gett(key, defaultValue);
	}

	private static <T> T gett(String key, Object defaultValue) {
		T v;
		return (((v = (T) config.getProperty(key)) != null) || has(key)) ? v : (T) defaultValue;
	}

	static void investigate(Object o) {
		final Class<?> c = o.getClass();
		System.out.println("\n" + c.getName() + " implements: ");
		investigate(c, (Type[]) null);
	}

	static void investigate(Type t, Type... typeArgs) {
		if (t == null) {
			System.out.println("Nope sorry :(");
			return;
		}

		if (t instanceof Class<?>) {
			investigate((Class<?>) t, typeArgs);
		} else if (t instanceof ParameterizedType) {
			investigate((ParameterizedType) t, typeArgs);
		}
	}

	static void investigate(Class<?> c, Type... typeArgs) {
		investigate(c.getGenericSuperclass(), typeArgs);

		for (Type i : c.getGenericInterfaces()) {
			investigate(i, typeArgs);
		}
	}

	static void investigate(ParameterizedType p, Type... typeArgs) {
		final Class<?> c = (Class<?>) p.getRawType();
		final StringBuilder b = new StringBuilder(c.getName());
		b.append('<');
		Type[] localArgs = p.getActualTypeArguments();
		if (typeArgs != null && typeArgs.length > 0) {
			int i = 0, nextTypeArg = 0;
			for (Type local : localArgs) {
				if (local instanceof ParameterizedType) {
					ParameterizedType localP = (ParameterizedType) local;
					b.append(localP.getRawType()).append('<');
					b.append(typeArgs[nextTypeArg++]);
					b.append('>');
				} else if (local instanceof TypeVariable) {
					// reify local type arg to instantiated one.
					localArgs[nextTypeArg] = typeArgs[nextTypeArg];
					b.append(localArgs[nextTypeArg]);
					nextTypeArg++;
				} else {
					b.append(local.toString());
				}
				b.append(", ");
				i++;
			}
			if (typeArgs.length > 0) {
				b.delete(b.length() - 2, b.length());
			}
			b.append('>');
		} else {
			String args = Arrays.toString(localArgs);
			b.append(args.substring(1, args.length() - 1)).append('>');
		}
		System.out.println(b);
		investigate(c, localArgs);
	}

		/*TyluurPixaroo<T> tyluurPix = new TyluurPixaroo<T>();

		System.out.println("Pls work: " + tyluurPix.getEntityClass());*/
		/*Class<?> clazz = TypeResolver.resolveRawArgument(TyluurPix.class, TyluurPixaroo.class);
		System.out.println(clazz);*/

		/*Type t = TypeResolver.resolveGenericType(TyluurPix.class, tyluurPix.getType());
		System.out.println(t.getTypeName());*/
		/*try {
			Method method = Prefs.class.getDeclaredMethod("get", String.class);

			GenericsContext context = GenericsResolver.resolve(Prefs.class);
			MethodGenericsContext methodContext = context.method(method);
			T bla = (T) new Object();
			System.out.println("Bla: " + bla.getClass());
			System.out.println(methodContext.resolveReturnClass());
			System.out.println(methodContext.resolveParameters());
			//System.out.println(token.getType().getClass());
			//System.out.println(Boolean.class.isAssignableFrom(TypeLiteral.get(type)));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}*/
/*		return (T) config.getProperty(key);
	}*/

	private interface TyluurPix<T> {
		default T t() { // try to retain the type at compilation
			return (T) new Object();
		}
	}

	private static final class TyluurPixaroo<T> {
		private Class<T> entityClass;

		private Class<T> getEntityClass() {
			if (entityClass == null)
				entityClass = (Class<T>) TypeResolver.resolveRawArgument(TyluurPixaroo.class, getClass());
			return entityClass;
		}
	}

	public static boolean has(String key) {
		return config.containsKey(key);
	}

	public static void set(String key, Object value) {
		config.setProperty(key, value);
	}

	public static void remove(String key) {
		config.clearProperty(key);
	}

}
