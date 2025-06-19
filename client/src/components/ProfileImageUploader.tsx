import { useState } from "react";
import axios from "axios";
import { Button, CircularProgress, Typography } from "@mui/material";
import type { FileDto } from "../types/file";

export default function ProfileImageUploader({
  onUploadComplete,
}: {
  onUploadComplete: (uploadedFile: FileDto) => void;
}) {
  const [uploading, setUploading] = useState(false);
  const [error, setError] = useState("");

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    setUploading(true);
    setError("");

    axios
      .post<FileDto>(
        `${import.meta.env.VITE_API_BASE_URL}/api/files/profile`,
        formData,
        {
          headers: {
            "Content-Type": "multipart/form-data",
            Authorization: "Bearer " + sessionStorage.getItem("jwt"),
          },
        }
      )
      .then((response) => {
        onUploadComplete(response.data); // 업로드된 파일 이름 콜백으로 전달
      })
      .catch((err) => {
        console.error("파일 업로드 실패:", err);
        setError("파일 업로드 중 오류가 발생했습니다.");
      })
      .finally(() => {
        setUploading(false);
      });
  };

  return (
    <div className="flex flex-col items-start gap-2 ml-2">
      <input
        type="file"
        accept="image/*"
        id="profile-file-input"
        style={{ display: "none" }}
        onChange={handleFileChange}
      />
      <label htmlFor="profile-file-input">
        <Button variant="outlined" component="span" disabled={uploading}>
          프로필 이미지 선택
        </Button>
      </label>

      {uploading && <CircularProgress size={24} />}

      {error && (
        <Typography color="error" variant="body2">
          {error}
        </Typography>
      )}
    </div>
  );
}
