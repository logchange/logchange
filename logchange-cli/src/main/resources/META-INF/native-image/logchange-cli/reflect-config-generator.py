import json
import os


def find_java_classes(base_dir):
    entries = []
    for root, dirs, files in os.walk(base_dir):
        for file in files:
            if file.endswith(".java"):
                full_path = os.path.join(root, file)
                rel_path = os.path.relpath(full_path, base_dir)
                dotted_path = rel_path.replace(os.sep, ".")
                class_part = dotted_path[:-5]
                full_class_name = "dev.logchange.core.format.yml." + class_part
                entries.append({
                    "name": full_class_name,
                    "allDeclaredFields": True,
                    "allDeclaredMethods": True,
                    "allDeclaredConstructors": True,
                })
    return entries

def main():
    base_dir = "../../../../../../../logchange-core/src/main/java/dev/logchange/core/format/yml"
    reflect_config = find_java_classes(base_dir)
    with open("reflect-config.json", "w", encoding="utf-8") as f:
        json.dump(reflect_config, f, indent=2)

if __name__ == "__main__":
    main()
