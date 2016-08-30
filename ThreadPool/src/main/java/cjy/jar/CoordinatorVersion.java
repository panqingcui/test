package cjy.jar;

/**
 * <p>功能描述,该部分必须以中文句号结尾。<p>
 *
 * 创建日期 2016年7月26日<br>
 * @author $Author$<br>
 * @version $Revision$ $Date$
 * @since 1.0.0
 */
public class CoordinatorVersion {

    /*private static final CoordinatorVersion INSTANCE = new CoordinatorVersion(CoordinatorVersion.class);
    private final Manifest manifest;

    public CoordinatorVersion(Class<?> clazz) throws CoordinatorException {
        Manifest resolvedManifest = null;
        try {
            String jarUrl = resolveJarUrl(clazz);
            if (jarUrl != null) {
                resolvedManifest = loadManifest(jarUrl);
            }
        } catch (Throwable e) {
            throw new CoordinatorException(CoordinatorException.COORDINATOR_VERSION_EXCEPTION, "解析jar时异常", e);
        }
        this.manifest = resolvedManifest;
    }

    String getBuildVersion() {
        return getManifestAttribute("Implementation-Version", "<version_unknown>");
    }

    String getManifestAttribute(String name, String defaultValue) {
        if (manifest == null) {
            return defaultValue;
        }
        Name attrName = new Name(name);
        Object value = manifest.getMainAttributes().get(attrName);
        return value == null ? defaultValue : value.toString();
    }

    public static String buildVersion() {
        return INSTANCE.getBuildVersion();
    }

    private static Manifest loadManifest(String jarUrl) throws Exception {
        InputStream is = new URL(jarUrl + "!/META-INF/MANIFEST.MF").openStream();
        try {
            return new Manifest(is);
        } finally {
            is.close();
        }
    }

    private static String resolveJarUrl(Class<?> clazz) {
        URL location = clazz.getResource('/' + clazz.getName().replace('.', '/') + ".class");
        if (location != null) {
            Matcher matcher = Pattern.compile("(jar:file.*-[\\d.]+(-SNAPSHOT)?.jar)!.*$").matcher(location.toString());
            if (matcher.matches()) {
                return matcher.group(1);
            }
        }
        return null;
    }*/

}
