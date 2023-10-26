# Web Scraping PDF Image Extractor

## Project Overview

Web Scraping PDF Image Extractor is a Java-based web scraping project designed to search for PDF files on a specified website, extract the first n pages of each PDF document as images, and store essential information such as the PDF address and file size. This tool is a valuable utility for tasks like archiving web content or cataloging PDF resources on the web.

## Features

- **Web Scraping:** The project uses web scraping techniques to search and identify PDF files on a given website.

- **PDF File Detection:** It analyzes web pages to identify and filter PDF files based on file extensions and content.

- **PDF Download:** The tool downloads the PDF files to a local directory for further processing.

- **PDF to Image Conversion:** It converts the first 10 pages of each PDF into images, preserving the visual content.

- **Metadata Storage:** The project stores essential information about each PDF, including the PDF address (URL) and the file size.

- **Customizable Configuration:** Users can customize the tool to specify the website to scrape, the directory for PDF storage, and other parameters.

## Requirements
- Java Development Kit (JDK) 8 or higher
- Maven 3+ for dependencies management
  
## Installation and Usage

1. **Clone the Repository:** Clone this repository to your local machine.
    ```bash
      git clone https://github.com/mengstabketemaw/book-fetcher-and-loader.git

2. Install Dependencies: Navigate to the project directory and install the required dependencies using Maven (Make sure you have Maven installed).
    ```bash
      cd book-fetcher-and-loader
      mvn install

3. Build the Project: Build the Java application using Maven.
    ```bash
      mvn package

4. Run the Jar: Run the compiled JAR file to execute the web scraper replace the <url> and <page-number> with the actual parametre.
    ```bash
      java -jar target/book-fetcher-and-loaderr.jar <url> <page-number>
    
5. Results: The PDFs will be downloaded, converted to images, and stored in the specified directory, along with the associated PDF address and size information.

## Disclaimer
Please use this tool responsibly and adhere to all legal and ethical guidelines when scraping websites. Be aware of the website's terms of service and consider seeking permission when necessary.


