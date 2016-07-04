package com.fusw.mvc.util;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

/**
 * @author 付施威
 * @version V1.0
 * @SystemName UTB-CLOUD
 * @ModuleName com.fusw.mvc.util
 * @Date 16/6/13上午7:34
 * @Description 描述
 */
public final class ClassUtil {

	public static ClassLoader getClassLoader() {

		return Thread.currentThread().getContextClassLoader();
	};

	public static Class<?> loadClass(String className, boolean isInitialized) {

		Class<?> cls = null;

		try {
			cls = Class.forName(className, true, getClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return cls;
	};

	public static Set<Class<?>> getClassSet(String packageName) {

		Set<Class<?>> classSet = new HashSet<Class<?>>();

		try {
			Enumeration<URL> urls = getClassLoader().getResources(packageName.replace(".", "/"));

			while (urls.hasMoreElements()) {
				URL url = urls.nextElement();
				String protocol = url.getProtocol();// 获取协议,即文件类型

				if (protocol.equals("file")) {

					String packagePath = url.getPath().replaceAll("%20", " ");// 还原路径中被编码的空格
					addClass(classSet,packagePath,packageName);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return classSet;
	}

	public static void addClass(Set<Class<?>> classSet, String packagePath, String packageName) {

		File[] files = new File(packagePath).listFiles(new FileFilter() {

			public boolean accept(File file) {

				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});

		for (File file : files) {

			String fileName = file.getName();

			if (file.isFile()) {

				String className = fileName.substring(0, fileName.lastIndexOf("."));// 截取.class前面的部分

				if (StringUtils.isNotEmpty(packageName)) {// 若传入的包名不为空则将classname加到上包名前缀

					className = packageName + "." + className;
				}

				doAddClass(classSet, className);// 载入类
			} else {// 若是文件夹则

				String dirctoryFilePath = fileName;// 若是文件下

				if (StringUtils.isNotEmpty(packageName)) {// 若传入的包名不为空则将classname加到上包名前缀

					dirctoryFilePath = packageName + "." + dirctoryFilePath;
				}

				String dirctoryFilePakeageName = fileName;

				if (StringUtils.isNotEmpty(packageName)) {// 若传入的包名不为空则将classname加到上包名前缀

					dirctoryFilePakeageName = packageName + "." + dirctoryFilePakeageName;
				}

				addClass(classSet, dirctoryFilePath, dirctoryFilePakeageName);
			}

		}

	}

	public static void doAddClass(Set<Class<?>> classSet, String className) {

		Class<?> cls = loadClass(className, false);

		classSet.add(cls);
	}
}
