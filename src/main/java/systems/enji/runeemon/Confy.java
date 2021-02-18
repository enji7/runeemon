package systems.enji.runeemon;

/**
 * Provides configuration data for the other operations.
 */
class Confy {

  private static RuntimeData[] runtimeList = {
      new RuntimeData(
          "wildfly", 
          "22.0.1.Final", 
          "https://download.jboss.org/wildfly/22.0.1.Final/wildfly-22.0.1.Final.zip",
          "/standalone/deployments/",
          "/bin/standalone.sh",
          "",
          "8080",
          "",
          ""),
      new RuntimeData(
          "payara", 
          "5.2021.1", 
          "https://info.payara.fish/cs/c/?cta_guid=b9609f35-f630-492f-b3c0-238fc55f489b&signature=AAH58kEd4fsx9dq9eUnYe1MhS12YQGzTsQ&placement_guid=7cca6202-06a3-4c29-aee0-ca58af60528a&click=5e109529-656c-43c1-a9ee-25f369d4c0b1&hsutk=281ef6723730511bb62965ed2ef3aa24&canon=https%3A%2F%2Fwww.payara.fish%2Fdownloads%2Fpayara-platform-community-edition%2F&utm_referrer=https%3A%2F%2Fwww.payara.fish%2Fdownloads%2F&portal_id=334594&redirect_url=APefjpF3p_78bY07iI0he9x3VtyBNGj-kykiyGs594RuIjfCtPnea-hCv8P7essX3k9n2FADE9R1vsd0nLfMqZyVfWx4ntZIp7jZSPDaMYdQ23AJNqwo6nHMH6v8Y-StKVTxV0_Zk13RC7zDeJvII4stzgEMS75Uvr3RXb6quj8u4EE0Z-CxWhiIlyx7WRymlQo9-i-VkNu7QMPsrhkaBh9btlTwGas9UFSiiIY0-VrzL1pFSIDPqMVvrnSb301I6RWlCrIg9hFYctZQBzqXoQwfdeTmO8rCQEiBGGHbJV_ZMp-FFqIZZrtsBAC8FPRipcdk81FpxL_dUmpgWYmE5tqSfmjjCVFqVA&__hstc=229474563.281ef6723730511bb62965ed2ef3aa24.1608706039555.1611999495005.1613545091113.3&__hssc=229474563.4.1613545091113&__hsfp=3693986414",
          "/domains/domain1/autodeploy/",
          "/bin/asadmin start-domain",
          "/bin/asadmin stop-domain",
          "8080",
          "",
          ""),
      // TODO: check TomEE auto-deploy
      new RuntimeData(
          "tomee", 
          "8.0.6", 
          "https://mirror.klaus-uwe.me/apache/tomee/tomee-8.0.6/apache-tomee-8.0.6-plume.zip",
          "/webapps/",
          "/bin/startup.sh",
          "/bin/shutdown.sh",
          "8080",
          "http://localhost:8080/",
          "/logs/")
  };
  
  /**
   * Returns a list containing the meta data of all configured runtimes.
   */
  static RuntimeData[] getRuntimeList() {
    return runtimeList;
  }

}
