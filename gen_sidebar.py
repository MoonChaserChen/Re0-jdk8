import os
from shutil import copyfile
import codecs

suffix = ".md"
gen_file_name = "summary.md"
doc_path = "docs"
read_me_file = "README.md"
cname_file = "CNAME"

ignore_files = [gen_file_name, ".git", read_me_file]


def print_file(c_dir, depth, write2):
    for f in os.listdir(c_dir):
        re_f = os.path.join(c_dir, f)
        is_d = os.path.isdir(re_f)
        if is_d and f not in ignore_files:
            write2.write("\t" * depth + "- " + f + "\n")
            print_file(re_f, depth + 1, write2)
        else:
            if suffix in f and f not in ignore_files:
                f_n = os.path.splitext(f)[0]
                write2.write("\t" * depth + "- [" + f_n + "](/" + re_f[2:] + ")\n")


os.chdir(doc_path)
copyfile("../" + read_me_file, read_me_file)
copyfile("../" + cname_file, cname_file)
g_f = codecs.open(gen_file_name, 'w', encoding='utf-8')
print_file(".", 0, g_f)
g_f.close()
