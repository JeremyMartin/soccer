package fr.ewaux.scrapping.service.file;

public interface FileService {

  void writeFile(String pathname, String filename, Object body);

  void writeFile(String pathname, String filename, byte[] bytes);

  void writeXls(Class<?>... targets);

}
