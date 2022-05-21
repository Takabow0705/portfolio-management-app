import React, { useEffect, useState } from "react";
import { Table } from "react-bootstrap";
import { upload } from "../../utils/stockExecution";

// https://www.webopixel.net/javascript/1533.html
export const ExecutionInquiry = () => {
  return (
    <div>
      <h2 className={"col-md-6 pt-3"}>株式約定照会</h2>
      <hr></hr>
      <div className={"col-md-10 pt-3"}>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
          <tr>
            <td>3</td>
            <td colSpan={2}>Larry the Bird</td>
            <td>@twitter</td>
          </tr>
        </tbody>
      </Table>
      <button className={"btn btn-outline-secondary col-md-1 pt-2"} type="submit" name="submit" value="submit">ダウンロード</button>
      </div>
    </div>
  );
};
