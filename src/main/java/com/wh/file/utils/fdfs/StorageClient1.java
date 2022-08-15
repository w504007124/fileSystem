package com.wh.file.utils.fdfs;

/**
 * 什么？我是真滴帅
 *
 * @author WuHao on 15:14 2022/5/11
 */

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StorageClient1 extends StorageClient {
    public static final String SPLIT_GROUP_NAME_AND_FILENAME_SEPERATOR = "/";

    public StorageClient1() {
    }

    public StorageClient1(TrackerServer trackerServer, StorageServer storageServer) {
        super(trackerServer, storageServer);
    }

    public static byte split_file_id(String file_id, String[] results) {
        int pos = file_id.indexOf("/");
        if (pos > 0 && pos != file_id.length() - 1) {
            results[0] = file_id.substring(0, pos);
            results[1] = file_id.substring(pos + 1);
            return 0;
        } else {
            return 22;
        }
    }

    public String upload_file1(String local_filename, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_file(local_filename, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_file1(String group_name, String local_filename, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_file(group_name, local_filename, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_file1(byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_file(file_buff, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_file1(String group_name, byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_file(group_name, file_buff, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_file1(String group_name, long file_size, UploadCallback callback, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_file(group_name, file_size, callback, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_appender_file1(String local_filename, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_appender_file(local_filename, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_appender_file1(String group_name, String local_filename, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_appender_file(group_name, local_filename, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_appender_file1(byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_appender_file(file_buff, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_appender_file1(String group_name, byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_appender_file(group_name, file_buff, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_appender_file1(String group_name, long file_size, UploadCallback callback, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = this.upload_appender_file(group_name, file_size, callback, file_ext_name, meta_list);
        return parts != null ? parts[0] + "/" + parts[1] : null;
    }

    public String upload_file1(String master_file_id, String prefix_name, String local_filename, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(master_file_id, parts);
        if (this.errno != 0) {
            return null;
        } else {
            parts = this.upload_file(parts[0], parts[1], prefix_name, local_filename, file_ext_name, meta_list);
            return parts != null ? parts[0] + "/" + parts[1] : null;
        }
    }

    public String upload_file1(String master_file_id, String prefix_name, byte[] file_buff, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(master_file_id, parts);
        if (this.errno != 0) {
            return null;
        } else {
            parts = this.upload_file(parts[0], parts[1], prefix_name, file_buff, file_ext_name, meta_list);
            return parts != null ? parts[0] + "/" + parts[1] : null;
        }
    }

    public String upload_file1(String master_file_id, String prefix_name, byte[] file_buff, int offset, int length, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(master_file_id, parts);
        if (this.errno != 0) {
            return null;
        } else {
            parts = this.upload_file(parts[0], parts[1], prefix_name, file_buff, offset, length, file_ext_name, meta_list);
            return parts != null ? parts[0] + "/" + parts[1] : null;
        }
    }

    public String upload_file1(String master_file_id, String prefix_name, long file_size, UploadCallback callback, String file_ext_name, NameValuePair[] meta_list) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(master_file_id, parts);
        if (this.errno != 0) {
            return null;
        } else {
            parts = this.upload_file(parts[0], parts[1], prefix_name, file_size, callback, file_ext_name, meta_list);
            return parts != null ? parts[0] + "/" + parts[1] : null;
        }
    }

    public int append_file1(String appender_file_id, String local_filename) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.append_file(parts[0], parts[1], local_filename);
    }

    public int append_file1(String appender_file_id, byte[] file_buff) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.append_file(parts[0], parts[1], file_buff);
    }

    public int append_file1(String appender_file_id, byte[] file_buff, int offset, int length) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.append_file(parts[0], parts[1], file_buff, offset, length);
    }

    public int append_file1(String appender_file_id, long file_size, UploadCallback callback) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.append_file(parts[0], parts[1], file_size, callback);
    }

    public int modify_file1(String appender_file_id, long file_offset, String local_filename) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.modify_file(parts[0], parts[1], file_offset, local_filename);
    }

    public int modify_file1(String appender_file_id, long file_offset, byte[] file_buff) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.modify_file(parts[0], parts[1], file_offset, file_buff);
    }

    public int modify_file1(String appender_file_id, long file_offset, byte[] file_buff, int buffer_offset, int buffer_length) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.modify_file(parts[0], parts[1], file_offset, file_buff, buffer_offset, buffer_length);
    }

    public int modify_file1(String appender_file_id, long file_offset, long modify_size, UploadCallback callback) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.modify_file(parts[0], parts[1], file_offset, modify_size, callback);
    }

    public int delete_file1(String file_id) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? this.errno : this.delete_file(parts[0], parts[1]);
    }

    public int truncate_file1(String appender_file_id) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.truncate_file(parts[0], parts[1]);
    }

    public int truncate_file1(String appender_file_id, long truncated_file_size) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(appender_file_id, parts);
        return this.errno != 0 ? this.errno : this.truncate_file(parts[0], parts[1], truncated_file_size);
    }

    public byte[] download_file1(String file_id) throws IOException, MyException {
        long file_offset = 0L;
        long download_bytes = 0L;
        return this.download_file1(file_id, 0L, 0L);
    }

    public byte[] download_file1(String file_id, long file_offset, long download_bytes) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? null : this.download_file(parts[0], parts[1], file_offset, download_bytes);
    }

    public int download_file1(String file_id, String local_filename) throws IOException, MyException {
        long file_offset = 0L;
        long download_bytes = 0L;
        return this.download_file1(file_id, 0L, 0L, local_filename);
    }

    public int download_file1(String file_id, long file_offset, long download_bytes, String local_filename) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? this.errno : this.download_file(parts[0], parts[1], file_offset, download_bytes, local_filename);
    }

    public int download_file1(String file_id, DownloadCallback callback) throws IOException, MyException {
        long file_offset = 0L;
        long download_bytes = 0L;
        return this.download_file1(file_id, 0L, 0L, callback);
    }

    public int download_file1(String file_id, long file_offset, long download_bytes, DownloadCallback callback) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? this.errno : this.download_file(parts[0], parts[1], file_offset, download_bytes, callback);
    }

    public NameValuePair[] get_metadata1(String file_id) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? null : this.get_metadata(parts[0], parts[1]);
    }

    public int set_metadata1(String file_id, NameValuePair[] meta_list, byte op_flag) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? this.errno : this.set_metadata(parts[0], parts[1], meta_list, op_flag);
    }

    public FileInfo query_file_info1(String file_id) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? null : this.query_file_info(parts[0], parts[1]);
    }

    public FileInfo get_file_info1(String file_id) throws IOException, MyException {
        String[] parts = new String[2];
        this.errno = split_file_id(file_id, parts);
        return this.errno != 0 ? null : this.get_file_info(parts[0], parts[1]);
    }
}
