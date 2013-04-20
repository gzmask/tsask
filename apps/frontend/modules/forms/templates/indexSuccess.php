<h1>Sa formss List</h1>

<table>
  <thead>
    <tr>
      <th>Id</th>
      <th>Form name</th>
      <th>Form content</th>
      <th>Form published</th>
      <th>Created at</th>
      <th>Updated at</th>
    </tr>
  </thead>
  <tbody>
    <?php foreach ($sa_formss as $sa_forms): ?>
    <tr>
      <td><a href="<?php echo url_for('forms/show?id='.$sa_forms->getId()) ?>"><?php echo $sa_forms->getId() ?></a></td>
      <td><?php echo $sa_forms->getFormName() ?></td>
      <td><?php echo $sa_forms->getFormContent() ?></td>
      <td><?php echo $sa_forms->getFormPublished() ?></td>
      <td><?php echo $sa_forms->getCreatedAt() ?></td>
      <td><?php echo $sa_forms->getUpdatedAt() ?></td>
    </tr>
    <?php endforeach; ?>
  </tbody>
</table>

  <a href="<?php echo url_for('forms/new') ?>">New</a>
