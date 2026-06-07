# Image Converter

It currently supports converting image formats like PNG and JPEG to **WebP** using lossy compression.

## Prerequisites

- **Java 17** (or higher)
- **Maven** (to compile the project)

## Build Instructions

To build the project and generate the executable standalone JAR (Fat JAR) with all required dependencies, run the following command in the project's root directory:

```bash
mvn clean package
```

This will create an executable JAR file inside the `target` directory named `image-converter-1.0-SNAPSHOT-shaded.jar` (or simply `image-converter-1.0-SNAPSHOT.jar` depending on the shade plugin replacement).

## Usage

You can run the application directly from the terminal by passing the path of the image you want to convert. The converted image will be saved in the same directory with the `.webp` extension.

### Basic Conversion (Default 80% Quality)
```bash
java -jar target/image-converter-1.0-SNAPSHOT.jar /path/to/your/image.png
```

### Custom Quality Conversion
You can optionally provide a second parameter to specify the compression quality (between `0.0` and `1.0`). For example, for 65% quality:
```bash
java -jar target/image-converter-1.0-SNAPSHOT.jar /path/to/your/image.jpg 0.65
```

### Example Output
```text
Starting conversion...
Input: /path/to/your/image.jpg
Output: /path/to/your/image.webp
Compression quality: 65.0%
Conversion successful in 187ms.
```
