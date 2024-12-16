import React from "react";
import { Document, Page, Text, View, StyleSheet } from "@react-pdf/renderer";

const styles = StyleSheet.create({
  page: {
    flexDirection: "column",
    backgroundColor: "#ffffff",
    padding: "30px",
    fontSize: "12pt",
  },
  section: {
    marginBottom: "10px",
  },
  title: {
    fontSize: "16pt",
    marginBottom: "10px",
  },
  subtitle: {
    fontSize: "14pt",
    marginBottom: "5px",
  },
  content: {
    fontSize: "12pt",
  },
});

const ResumePDF = ({ formData }) => {
  return (
    <Document>
      <Page size="A4" style={styles.page}>
        <View style={styles.section}>
          <Text style={styles.title}>Personal Details</Text>
          <Text style={styles.subtitle}>Name: </Text>
          <Text style={styles.content}>{formData.name}</Text>
          <Text style={styles.subtitle}>Email: </Text>
          <Text style={styles.content}>{formData.email_address}</Text>
          <Text style={styles.subtitle}>Phone: </Text>
          <Text style={styles.content}>{formData.phone_number}</Text>
          <Text style={styles.subtitle}>LinkedIn: </Text>
          <Text style={styles.content}>{formData.linkedin}</Text>
          <Text style={styles.subtitle}>GitHub: </Text>
          <Text style={styles.content}>{formData.github}</Text>
          <Text style={styles.subtitle}>Skills: </Text>
          <Text style={styles.content}>{formData.skills}</Text>
        </View>

//        {/* Add sections for Education, Experience, Projects, and Extras here */}
//        {/* You can use the same styling format to display the information */}
      </Page>
    </Document>
  );
};

export default ResumePDF;
